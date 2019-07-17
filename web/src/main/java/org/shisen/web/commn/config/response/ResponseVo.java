package org.shisen.web.commn.config.response;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * Description
 * </pre>
 *
 * @author shishi
 */
@Getter
@Setter
public class ResponseVo<T> {
    private long total;
    private int pageNum;
    private int size;
    private List<T> records;

    public ResponseVo(PageInfo<T> pageInfo) {
        this.total = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.size = pageInfo.getSize();
        this.records = pageInfo.getList();
    }

}
