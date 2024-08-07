package com.creeper.mapper;

import com.creeper.pojo.MethodInvocationCount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MethodInvocationCountMapper {
    @Insert("insert into method_invocation_count (class_name, method_name,invocation_count) " +
            "values (#{className}, #{methodName},#{invocationCount});")
    public void insert(MethodInvocationCount methodInvocationCount);

    @Select("SELECT * FROM method_invocation_count WHERE class_name = #{className} AND method_name = #{methodName}")
    MethodInvocationCount findByClassNameAndMethodName(String className, String methodName);

    @Update("UPDATE method_invocation_count SET invocation_count = #{invocationCount} WHERE id = #{id}")
    void updateInvocationCount(MethodInvocationCount count);
}
