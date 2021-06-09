package ${packpath};
/**
* ${tableconment}业务层
*/
import ${packageName}.common.vo.${entityName}.*;
import ${packageName}.common.dto.${entityName}.*;
import ${packageName}.entity.${entityName};
import ${packageName}.service.I${entityName}Service;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;


public class T${entityName}ServiceImpl extends AbstractServiceImpl<T${entityName}Mapper, T${entityName}, Long> implements IT${entityName}Service {
@Override
public PageSerializable<${entityName}TableVO> list(${entityName}SearchDTO ${entityName?lower_case}searchDTO, PageParamDTO pageParamDTO){
    return PageHelper.startPage(pageParamDTO).doSelectPageSerializable(() -> mapper.list(${entityName?lower_case}searchDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Create${entityName}DTO create${entityName?lower_case}DTO){
    final UserDetailDTO userDetailDTO = UserDetailContext.get();
    final Date now = new Date();
    final T${entityName} ${entityName?lower_case} = BeanCopierUtil.copy(create${entityName?lower_case}DTO, T${entityName}.class)
    .toBuilder()
    .createId(userDetailDTO.getUserId())
    .createName(userDetailDTO.getNickname())
    .handlerId(userDetailDTO.getUserId())
    .handlerName(userDetailDTO.getNickname())
    .createTime(now)
    .updateTime(now)
    .build();
    this.insertSelective(${entityName?lower_case});
    }

    @Override
    public void edit(Edit${entityName}DTO ${entityName?lower_case}DTO){
    final UserDetailDTO userDetailDTO = UserDetailContext.get();
    final Date now = new Date();
    }

    @Override
    public ${entityName}VO getDetail(String Id){

    }

}