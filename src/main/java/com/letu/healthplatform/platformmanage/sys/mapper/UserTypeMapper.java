package com.letu.healthplatform.platformmanage.sys.mapper;

import com.letu.healthplatform.platformmanage.sys.model.UserType;

public interface UserTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbggenerated Thu Nov 30 10:53:38 CST 2017
     */
    int deleteByPrimaryKey(Integer usertypeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbggenerated Thu Nov 30 10:53:38 CST 2017
     */
    int insert(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbggenerated Thu Nov 30 10:53:38 CST 2017
     */
    int insertSelective(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbggenerated Thu Nov 30 10:53:38 CST 2017
     */
    UserType selectByPrimaryKey(Integer usertypeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbggenerated Thu Nov 30 10:53:38 CST 2017
     */
    int updateByPrimaryKeySelective(UserType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_type
     *
     * @mbggenerated Thu Nov 30 10:53:38 CST 2017
     */
    int updateByPrimaryKey(UserType record);
}