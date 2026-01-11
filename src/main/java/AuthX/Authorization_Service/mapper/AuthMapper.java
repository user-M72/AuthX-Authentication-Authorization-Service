package AuthX.Authorization_Service.mapper;

import AuthX.Authorization_Service.dto.RegisterRequestDto;
import AuthX.Authorization_Service.dto.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    UserRequestDto toEntity(RegisterRequestDto dto);

}
