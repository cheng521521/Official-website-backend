package com.dl.officialsite.activity.bean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberTaskRecordRepository extends JpaRepository<MemberTaskRecord, Long>, JpaSpecificationExecutor<MemberTaskRecord> {


    @Query(value = "select * from member_task_record where address = :address and activity_name=:activityName", nativeQuery = true)
    List<MemberTaskRecord> findActivityRecordsByAddress(@Param("activityName") String activityName, @Param("address") String address);

    @Query(value = "select * from member_task_record where address = :address and activity_name=:activityName and " +
        "task_type=:taskType and target=:target", nativeQuery = true)
    List<MemberTaskRecord> findByAddressAndActivityNameAndTaskTypeAndTarget(@Param("address") String address,
                                                                            @Param("activityName") String activityName,
                                                                            @Param("taskType") String taskType,
                                                                            @Param("target") String target);

    @Modifying
    @Transactional
    @Query(value = "delete from member_task_record where address = :address and activity_name=:activityName", nativeQuery = true)
    Optional<Integer> deleteByAddressAndActivityName(@Param("address") String address,
                                                     @Param("activityName") String activityName);

}
