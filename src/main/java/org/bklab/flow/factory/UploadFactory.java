package org.bklab.flow.factory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.upload.*;
import elemental.json.Json;
import org.bklab.flow.FlowFactory;
import org.bklab.flow.base.GeneratedVaadinUploadFactory;
import org.bklab.flow.base.HasSizeFactory;

public class UploadFactory extends FlowFactory<Upload, UploadFactory> implements
        GeneratedVaadinUploadFactory<Upload, UploadFactory>, HasSizeFactory<Upload, UploadFactory> {
    public UploadFactory() {
        this(new Upload());
    }

    public UploadFactory(Receiver receiver) {
        this(new Upload(receiver));
    }

    public UploadFactory(Upload component) {
        super(component);
    }

    public UploadFactory clearUploadFiles() {
        get().getElement().setPropertyJson("files", Json.createArray());
        return this;
    }

    public UploadFactory dropAllowed(boolean dropAllowed) {
        get().setDropAllowed(dropAllowed);
        return this;
    }

    public UploadFactory maxFileSize(int maxFileSize) {
        get().setMaxFileSize(maxFileSize);
        return this;
    }

    public UploadFactory autoUpload(boolean autoUpload) {
        get().setAutoUpload(autoUpload);
        return this;
    }

    public UploadFactory interruptUpload() {
        get().interruptUpload();
        return this;
    }

    public UploadFactory dropLabel(Component dropLabel) {
        get().setDropLabel(dropLabel);
        return this;
    }

    public UploadFactory dropLabelIcon(Component dropLabelIcon) {
        get().setDropLabelIcon(dropLabelIcon);
        return this;
    }

    public UploadFactory uploadButton(Component uploadButton) {
        get().setUploadButton(uploadButton);
        return this;
    }

    public UploadFactory maxFiles(int maxFiles) {
        get().setMaxFiles(maxFiles);
        return this;
    }

    public UploadFactory progressListener(ComponentEventListener<ProgressUpdateEvent> progressListener) {
        get().addProgressListener(progressListener);
        return this;
    }

    public UploadFactory acceptedFileTypes(String[] acceptedFileTypes) {
        get().setAcceptedFileTypes(acceptedFileTypes);
        return this;
    }

    public UploadFactory finishedListener(ComponentEventListener<FinishedEvent> finishedListener) {
        get().addFinishedListener(finishedListener);
        return this;
    }

    public UploadFactory succeededListener(ComponentEventListener<SucceededEvent> succeededListener) {
        get().addSucceededListener(succeededListener);
        return this;
    }

    public UploadFactory fileRejectedListener(ComponentEventListener<FileRejectedEvent> fileRejectedListener) {
        get().addFileRejectedListener(fileRejectedListener);
        return this;
    }

    public UploadFactory allFinishedListener(ComponentEventListener<AllFinishedEvent> allFinishedListener) {
        get().addAllFinishedListener(allFinishedListener);
        return this;
    }

    public UploadFactory failedListener(ComponentEventListener<FailedEvent> failedListener) {
        get().addFailedListener(failedListener);
        return this;
    }

    public UploadFactory startedListener(ComponentEventListener<StartedEvent> startedListener) {
        get().addStartedListener(startedListener);
        return this;
    }

    public UploadFactory i18n(UploadI18N i18n) {
        get().setI18n(i18n);
        return this;
    }

    public UploadFactory receiver(Receiver receiver) {
        get().setReceiver(receiver);
        return this;
    }


}
