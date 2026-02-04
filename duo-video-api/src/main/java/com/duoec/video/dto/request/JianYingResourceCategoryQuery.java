package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class JianYingResourceCategoryQuery implements Serializable {
    /**
     * 资源类型：text_template=文本模板 flower=花字 transition=转场特效 mask=蒙板 video_effect=画面特效 face_effect=脸部特效 sticker=贴纸 sound=特效音
     */
    private String type;
}
