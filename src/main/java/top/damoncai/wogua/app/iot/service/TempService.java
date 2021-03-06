package top.damoncai.wogua.app.iot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.damoncai.wogua.app.blog.entity.BlogArticleTag;
import top.damoncai.wogua.app.iot.entity.Temp;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/6/14 16:39
 */
public interface TempService extends IService<Temp> {

    /**
     * 根据数据源和限定个数查询
     * @param sn
     * @param limit
     * @return
     */
    List<Temp> listBySourceLimt(String sn, int limit);
}
