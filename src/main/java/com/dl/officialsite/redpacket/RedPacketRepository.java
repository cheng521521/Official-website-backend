package com.dl.officialsite.redpacket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RedPacketRepository extends JpaRepository<RedPacket, String>,  JpaSpecificationExecutor <RedPacket> {


    @Query(value = "select * from red_packet where address_list like ?1", nativeQuery = true)
    List<RedPacket> findByAddress(@Param("address") String address);

    @Query(value = "select * from red_packet where address_list like ?1 and  status = ?2 ", nativeQuery = true)
    List<RedPacket> findByAddressAndStatus(@Param("address") String address, @Param("status") Integer status);


    @Query(value = "select * from red_packet where claimed_address like ?1 and chain_id = ?2 order by create_time desc", nativeQuery = true)
    List<RedPacket> findByClaimedPacket(@Param("address") String address, @Param("chainId") String chainId);

    @Query(value = "select * from red_packet where claimed_address like ?1 and if(isnull(?2), 1=1, chain_id = ?2) order by create_time desc", nativeQuery = true)
    List<RedPacket> findByClaimedPacketWithoutChainId(@Param("address") String address, @Param("chainId") String chainId);


    @Query(value = "select * from red_packet where address_list like ?1 and  claimed_address not like ?1 and  status = 2 and chain_id = ?2  order by create_time desc", nativeQuery = true)
    List<RedPacket> findByUnclaimedTimeOutPacket(@Param("address") String address, @Param("chainId") String chainId );

    // @Query(value = "select * from red_packet where address_list like ?1 and  claimed_address not like ?1 and status = ?2 and expire_time > UNIX_TIMESTAMP(current_timestamp()) ", nativeQuery = true)
    @Query(value = "select * from red_packet where address_list like ?1 and  claimed_address not like ?1 and status = ?2  and chain_id = ?3  order by create_time desc", nativeQuery = true)
    List<RedPacket> findByUnclaimedPacket(@Param("address") String address, @Param("status") Integer status, @Param("chainId") String chainId);

    @Query(value = "select * from red_packet where address_list like ?1 and  claimed_address not like ?1 and status = ?2  and if(isnull(?3), 1=1, chain_id = ?3)  order by create_time desc", nativeQuery = true)
    List<RedPacket> findByUnclaimedPacketWithoutChainId(@Param("address") String address, @Param("status") Integer status, @Param("chainId") String chainId);


    RedPacket findByIdAndStatus(@Param("id") String id,  @Param("status") Integer status);

    List<RedPacket> findByStatusAndChainId(@Param("status") Integer status, @Param("chainId") String chainId);


    @Query(value = "select * from red_packet where   (status IN (0,2)   or status is null)   and chain_id = ?1  order by create_time desc", nativeQuery = true)
    List<RedPacket> findUnfinishedRedpacketByChainId(@Param("chainId") String chainId);

}

