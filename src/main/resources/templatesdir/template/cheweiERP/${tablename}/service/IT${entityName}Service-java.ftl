package ${packpath};
/**
* ${tableconment}业务层
*/
import ${packageName}.common.vo.${sentityName}.*;
import ${packageName}.common.dto.${sentityName}.*;
import ${packageName}.entity.T${entityName};
import ${packageName}.service.IT${entityName}Service;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;


public interface IT${entityName}Service  extends IBaseService<T${entityName}Mapper, T${entityName}, Long> {

    PageSerializable<${entityName}<#noparse>TableVO></#noparse> list(${entityName}SearchDTO ${entityName?lower_case}searchDTO, PageParamDTO pageParamDTO);

    void create(Create${entityName}DTO create${entityName?lower_case}DTO);

    void edit(Edit${entityName}DTO edit${entityName?lower_case}DTO);

    ${entityName}VO getDetail(Long Id);
}