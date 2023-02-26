package icu.clemon.common.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Result<List<T>> implements Serializable {

    private final long totalItem;
    private final long totalPage;
    private final long size;
    private final long page;
    @JsonIgnore
    private final long offset;

    public PageResult(List<T> content, Pageable pageable, long totalItem) {
        super();
        this.size = pageable.getPageSize();
        this.page = pageable.getPageNumber();
        this.totalItem = totalItem;
        this.totalPage = (this.totalItem + this.size - 1) / this.size;
        this.offset = (this.page >= this.totalPage) ? this.totalItem :pageable.getOffset();
        this.setData(content);
        this.setCode(ResultCode.CODE200.getCode());
        this.setTs(System.currentTimeMillis());
        this.setSuccess(true);
    }

    public static <T> PageResult<T> of(List<T> content, Pageable pageable, long total) {
        return new PageResult<>( content, pageable, total);
    }

}
