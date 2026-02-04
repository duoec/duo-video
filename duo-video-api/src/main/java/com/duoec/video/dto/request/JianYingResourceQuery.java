package com.duoec.video.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class JianYingResourceQuery implements Serializable {
    /**
     * 资源类型：text_template=文本模板 flower=花字 transition=转场特效 mask=蒙板 video_effect=画面特效 face_effect=脸部特效 sticker=贴纸 sound=特效音
     */
    @NotBlank(message = "资源类型不能为空")
    private String type;

    /**
     * 所属分类
     */
    private Integer categoryId;

    /**
     * 名称或ID搜索
     */
    private String keywords;

    /**
     * 文本模板段落数：0=不限制 100=大于3段
     */
    private Integer textCount;

    /**
     * 页码，1表示第一页
     *
     * @demo 1
     */
    private Integer pageNo;

    /**
     * 每页显示数量
     *
     * @demo 20
     */
    private Integer pageSize;
}
