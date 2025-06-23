package br.com.financialsys.backend.mapper;

import br.com.financialsys.backend.dto.UserDTO;
import br.com.financialsys.backend.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }
}
