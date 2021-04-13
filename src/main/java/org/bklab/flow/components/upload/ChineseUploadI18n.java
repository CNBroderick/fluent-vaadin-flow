package org.bklab.flow.components.upload;

import com.vaadin.flow.component.upload.UploadI18N;

public class ChineseUploadI18n extends UploadI18N {

    public ChineseUploadI18n() {
        this
                .setDropFiles(new DropFiles().setOne("拖动单个文件到此处").setMany("拖动多个文件到此处"))
                .setAddFiles(new AddFiles().setOne("添加文件").setMany("添加多个文件"))
                .setCancel("取消")
                .setError(new Error().setFileIsTooBig("文件过大").setIncorrectFileType("文件类型不匹配").setTooManyFiles("文件个数超出限制"))
                .setUploading(new Uploading()
                        .setStatus(new Uploading.Status().setProcessing("处理中").setStalled("中止").setHeld("上传中").setConnecting("连接中"))
                        .setRemainingTime(new Uploading.RemainingTime().setPrefix("剩余时间：").setUnknown("未知"))
                        .setError(new Uploading.Error().setServerUnavailable("服务器不可用").setForbidden("服务器禁止上传").setUnexpectedServerError("服务器未知错误")));

    }
}
