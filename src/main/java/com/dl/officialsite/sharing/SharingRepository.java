package com.dl.officialsite.sharing;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SharingRepository extends JpaRepository<Share, Long>, JpaSpecificationExecutor<Share> {

    @Query(value = "select * from share limit :offset, :limit", nativeQuery = true)
    List<Share> findAllSharesPaged(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "select count(*) from share", nativeQuery = true)
    int loadAllCount();

    @Query(value = "select * from share where member_address = :memberAddress limit :offset, :limit", nativeQuery = true)
    List<Share> findAllSharesByUidPaged(@Param("memberAddress") String memberAddress, @Param("offset") int offset,
            @Param("limit") int limit);

    @Query(value = "select count(*) from share where member_address = :memberAddress", nativeQuery = true)
    int loadCountByUid(@Param("memberAddress") String memberAddress);

    List<Share> findByIdInAndRewardStatus(List<Long> id, Integer rewardStatus);

    // @Query(value = "SELECT * FROM share by STR_TO_DATE(date,'%Y/%m/%d') desc,time
    // desc,id desc", countQuery = "SELECT count(*) FROM share", nativeQuery = true)
    @Query(value = "SELECT * FROM share order by date desc,time desc, id desc", countQuery = "SELECT count(*) FROM share", nativeQuery = true)
    Page<Share> findAllByPage(Pageable pageable);

    @Query(value = "SELECT presenter, COUNT(*) AS shareCount FROM share GROUP BY presenter ORDER"
        + " BY shareCount DESC LIMIT :rankNumber" ,nativeQuery = true)
    List<Object[]> findTopGroups(@Param("rankNumber") Integer rankNumber);

    //通过id将share表中的courseId字段置为null
    @Modifying
    @Transactional
    @Query("update Share s set s.courseId = null where s.id = :id")
    void updateCourseIdToNull(@Param("id") Long id);


    //通过idList将share表中的courseId字段置为null
    @Modifying
    @Transactional
    @Query("update Share s set s.courseId = null where s.id in :idList")
    void updateCourseIdToNull(@Param("idList") List<Long> idList);

    //通过courseId查询share表中的数据
    @Query(value = "select * from share s where s.course_id = :courseId", nativeQuery = true)
    List<Share> findByCourseId(Long courseId);

    @Query(value = "select * from share s where s.wish_id = :wishId", nativeQuery = true)
    Optional<Share> findByWishId(Long wishId);
}
