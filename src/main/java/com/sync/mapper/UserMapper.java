package com.sync.mapper;

import com.sync.model.User;
import com.sync.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserVO toUserVO(User user);
    User toUser(UserVO userVO);
}
