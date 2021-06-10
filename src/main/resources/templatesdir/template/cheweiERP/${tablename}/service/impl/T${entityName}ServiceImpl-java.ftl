package ${packpath};
/**
* ${tableconment}业务层
*/
import ${packageName}.common.vo.${sentityName}.*;
import ${packageName}.common.dto.${sentityName}.*;
import ${packageName}.entity.T${entityName};
import ${packageName}.service.IT${entityName}Service;
import ${packageName}.core.context.UserDetailDTO;
import ${packageName}.entity.TContractExtPark;
import ${packageName}.mapper.T${entityName}Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
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
    public void edit(Edit${entityName}DTO edit${entityName?lower_case}DTO){
    final UserDetailDTO userDetailDTO = UserDetailContext.get();
    final Date now = new Date();
    final T${entityName} ${entityName?lower_case} = BeanCopierUtil.copy(edit${entityName?lower_case}DTO, T${entityName}.class)
    .toBuilder()
    .handlerId(userDetailDTO.getUserId())
    .handlerName(userDetailDTO.getNickname())
    .updateTime(now)
    .build();
    this.updateByPrimaryKeySelective(${entityName?lower_case});

    }

    @Override
    public ${entityName}VO getDetail(Long Id){
    final T${entityName} ${sentityName} = this.selectOne(T${entityName}.builder().${pricolname}(Id).build());
    BusinessExceptionAssert.notNull(${sentityName}, "${tableconment}不存在");
    final ${entityName}VO ${entityName?lower_case}VO = BeanCopierUtil.copy(${sentityName}, ${entityName}VO.class);
    return ${entityName?lower_case}VO;
    }

}