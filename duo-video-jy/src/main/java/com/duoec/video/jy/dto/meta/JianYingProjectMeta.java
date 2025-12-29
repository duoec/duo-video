
package com.duoec.video.jy.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class JianYingProjectMeta implements Serializable {

    @JsonProperty("cloud_package_completed_time")
    public String cloudPackageCompletedTime;

    @JsonProperty("draft_cloud_capcut_purchase_info")
    public String draftCloudCapcutPurchaseInfo;

    @JsonProperty("draft_cloud_last_action_download")
    public Boolean draftCloudLastActionDownload;

    @JsonProperty("draft_cloud_materials")
    public List<Object> draftCloudMaterials;

    @JsonProperty("draft_cloud_purchase_info")
    public String draftCloudPurchaseInfo;

    @JsonProperty("draft_cloud_template_id")
    public String draftCloudTemplateId;

    @JsonProperty("draft_cloud_tutorial_info")
    public String draftCloudTutorialInfo;

    @JsonProperty("draft_cloud_videocut_purchase_info")
    public String draftCloudVideocutPurchaseInfo;

    @JsonProperty("draft_cover")
    public String draftCover;

    @JsonProperty("draft_deeplink_url")
    public String draftDeeplinkUrl;

    @JsonProperty("draft_enterprise_info")
    public DraftEnterpriseInfo draftEnterpriseInfo;

    @JsonProperty("draft_fold_path")
    public String draftFoldPath;

    @JsonProperty("draft_id")
    public String draftId;

    @JsonProperty("draft_is_ai_packaging_used")
    public Boolean draftIsAiPackagingUsed;

    @JsonProperty("draft_is_ai_shorts")
    public Boolean draftIsAiShorts;

    @JsonProperty("draft_is_article_video_draft")
    public Boolean draftIsArticleVideoDraft;

    @JsonProperty("draft_is_from_deeplink")
    public String draftIsFromDeeplink;

    @JsonProperty("draft_is_invisible")
    public Boolean draftIsInvisible;

    @JsonProperty("draft_materials")
    public List<DraftMaterial> draftMaterials;

    @JsonProperty("draft_materials_copied_info")
    public List<DraftMaterialsCopiedInfo> draftMaterialsCopiedInfo;

    @JsonProperty("draft_name")
    public String draftName;

    @JsonProperty("draft_new_version")
    public String draftNewVersion;

    @JsonProperty("draft_removable_storage_device")
    public String draftRemovableStorageDevice;

    @JsonProperty("draft_root_path")
    public String draftRootPath;

    @JsonProperty("draft_segment_extra_info")
    public List<Object> draftSegmentExtraInfo;

    @JsonProperty("draft_timeline_materials_size_")
    public Integer draftTimelineMaterialsSize;

    @JsonProperty("draft_type")
    public String draftType;

    @JsonProperty("tm_draft_cloud_completed")
    public String tmDraftCloudCompleted;

    @JsonProperty("tm_draft_cloud_modified")
    public Integer tmDraftCloudModified;

    @JsonProperty("tm_draft_create")
    public Long tmDraftCreate;

    @JsonProperty("tm_draft_modified")
    public Long tmDraftModified;

    @JsonProperty("tm_draft_removed")
    public Integer tmDraftRemoved;

    @JsonProperty("tm_duration")
    public Integer tmDuration;

    public String getCloudPackageCompletedTime() {
        return cloudPackageCompletedTime;
    }

    public JianYingProjectMeta setCloudPackageCompletedTime(String cloudPackageCompletedTime) {
        this.cloudPackageCompletedTime = cloudPackageCompletedTime;
        return this;
    }

    public String getDraftCloudCapcutPurchaseInfo() {
        return draftCloudCapcutPurchaseInfo;
    }

    public JianYingProjectMeta setDraftCloudCapcutPurchaseInfo(String draftCloudCapcutPurchaseInfo) {
        this.draftCloudCapcutPurchaseInfo = draftCloudCapcutPurchaseInfo;
        return this;
    }

    public Boolean getDraftCloudLastActionDownload() {
        return draftCloudLastActionDownload;
    }

    public JianYingProjectMeta setDraftCloudLastActionDownload(Boolean draftCloudLastActionDownload) {
        this.draftCloudLastActionDownload = draftCloudLastActionDownload;
        return this;
    }

    public List<Object> getDraftCloudMaterials() {
        return draftCloudMaterials;
    }

    public JianYingProjectMeta setDraftCloudMaterials(List<Object> draftCloudMaterials) {
        this.draftCloudMaterials = draftCloudMaterials;
        return this;
    }

    public String getDraftCloudPurchaseInfo() {
        return draftCloudPurchaseInfo;
    }

    public JianYingProjectMeta setDraftCloudPurchaseInfo(String draftCloudPurchaseInfo) {
        this.draftCloudPurchaseInfo = draftCloudPurchaseInfo;
        return this;
    }

    public String getDraftCloudTemplateId() {
        return draftCloudTemplateId;
    }

    public JianYingProjectMeta setDraftCloudTemplateId(String draftCloudTemplateId) {
        this.draftCloudTemplateId = draftCloudTemplateId;
        return this;
    }

    public String getDraftCloudTutorialInfo() {
        return draftCloudTutorialInfo;
    }

    public JianYingProjectMeta setDraftCloudTutorialInfo(String draftCloudTutorialInfo) {
        this.draftCloudTutorialInfo = draftCloudTutorialInfo;
        return this;
    }

    public String getDraftCloudVideocutPurchaseInfo() {
        return draftCloudVideocutPurchaseInfo;
    }

    public JianYingProjectMeta setDraftCloudVideocutPurchaseInfo(String draftCloudVideocutPurchaseInfo) {
        this.draftCloudVideocutPurchaseInfo = draftCloudVideocutPurchaseInfo;
        return this;
    }

    public String getDraftCover() {
        return draftCover;
    }

    public JianYingProjectMeta setDraftCover(String draftCover) {
        this.draftCover = draftCover;
        return this;
    }

    public String getDraftDeeplinkUrl() {
        return draftDeeplinkUrl;
    }

    public JianYingProjectMeta setDraftDeeplinkUrl(String draftDeeplinkUrl) {
        this.draftDeeplinkUrl = draftDeeplinkUrl;
        return this;
    }

    public DraftEnterpriseInfo getDraftEnterpriseInfo() {
        return draftEnterpriseInfo;
    }

    public JianYingProjectMeta setDraftEnterpriseInfo(DraftEnterpriseInfo draftEnterpriseInfo) {
        this.draftEnterpriseInfo = draftEnterpriseInfo;
        return this;
    }

    public String getDraftFoldPath() {
        return draftFoldPath;
    }

    public JianYingProjectMeta setDraftFoldPath(String draftFoldPath) {
        this.draftFoldPath = draftFoldPath;
        return this;
    }

    public String getDraftId() {
        return draftId;
    }

    public JianYingProjectMeta setDraftId(String draftId) {
        this.draftId = draftId;
        return this;
    }

    public Boolean getDraftIsAiPackagingUsed() {
        return draftIsAiPackagingUsed;
    }

    public JianYingProjectMeta setDraftIsAiPackagingUsed(Boolean draftIsAiPackagingUsed) {
        this.draftIsAiPackagingUsed = draftIsAiPackagingUsed;
        return this;
    }

    public Boolean getDraftIsAiShorts() {
        return draftIsAiShorts;
    }

    public JianYingProjectMeta setDraftIsAiShorts(Boolean draftIsAiShorts) {
        this.draftIsAiShorts = draftIsAiShorts;
        return this;
    }

    public Boolean getDraftIsArticleVideoDraft() {
        return draftIsArticleVideoDraft;
    }

    public JianYingProjectMeta setDraftIsArticleVideoDraft(Boolean draftIsArticleVideoDraft) {
        this.draftIsArticleVideoDraft = draftIsArticleVideoDraft;
        return this;
    }

    public String getDraftIsFromDeeplink() {
        return draftIsFromDeeplink;
    }

    public JianYingProjectMeta setDraftIsFromDeeplink(String draftIsFromDeeplink) {
        this.draftIsFromDeeplink = draftIsFromDeeplink;
        return this;
    }

    public Boolean getDraftIsInvisible() {
        return draftIsInvisible;
    }

    public JianYingProjectMeta setDraftIsInvisible(Boolean draftIsInvisible) {
        this.draftIsInvisible = draftIsInvisible;
        return this;
    }

    public List<DraftMaterial> getDraftMaterials() {
        return draftMaterials;
    }

    public JianYingProjectMeta setDraftMaterials(List<DraftMaterial> draftMaterials) {
        this.draftMaterials = draftMaterials;
        return this;
    }

    public List<DraftMaterialsCopiedInfo> getDraftMaterialsCopiedInfo() {
        return draftMaterialsCopiedInfo;
    }

    public JianYingProjectMeta setDraftMaterialsCopiedInfo(List<DraftMaterialsCopiedInfo> draftMaterialsCopiedInfo) {
        this.draftMaterialsCopiedInfo = draftMaterialsCopiedInfo;
        return this;
    }

    public String getDraftName() {
        return draftName;
    }

    public JianYingProjectMeta setDraftName(String draftName) {
        this.draftName = draftName;
        return this;
    }

    public String getDraftNewVersion() {
        return draftNewVersion;
    }

    public JianYingProjectMeta setDraftNewVersion(String draftNewVersion) {
        this.draftNewVersion = draftNewVersion;
        return this;
    }

    public String getDraftRemovableStorageDevice() {
        return draftRemovableStorageDevice;
    }

    public JianYingProjectMeta setDraftRemovableStorageDevice(String draftRemovableStorageDevice) {
        this.draftRemovableStorageDevice = draftRemovableStorageDevice;
        return this;
    }

    public String getDraftRootPath() {
        return draftRootPath;
    }

    public JianYingProjectMeta setDraftRootPath(String draftRootPath) {
        this.draftRootPath = draftRootPath;
        return this;
    }

    public List<Object> getDraftSegmentExtraInfo() {
        return draftSegmentExtraInfo;
    }

    public JianYingProjectMeta setDraftSegmentExtraInfo(List<Object> draftSegmentExtraInfo) {
        this.draftSegmentExtraInfo = draftSegmentExtraInfo;
        return this;
    }

    public Integer getDraftTimelineMaterialsSize() {
        return draftTimelineMaterialsSize;
    }

    public JianYingProjectMeta setDraftTimelineMaterialsSize(Integer draftTimelineMaterialsSize) {
        this.draftTimelineMaterialsSize = draftTimelineMaterialsSize;
        return this;
    }

    public String getDraftType() {
        return draftType;
    }

    public JianYingProjectMeta setDraftType(String draftType) {
        this.draftType = draftType;
        return this;
    }

    public String getTmDraftCloudCompleted() {
        return tmDraftCloudCompleted;
    }

    public JianYingProjectMeta setTmDraftCloudCompleted(String tmDraftCloudCompleted) {
        this.tmDraftCloudCompleted = tmDraftCloudCompleted;
        return this;
    }

    public Integer getTmDraftCloudModified() {
        return tmDraftCloudModified;
    }

    public JianYingProjectMeta setTmDraftCloudModified(Integer tmDraftCloudModified) {
        this.tmDraftCloudModified = tmDraftCloudModified;
        return this;
    }

    public Long getTmDraftCreate() {
        return tmDraftCreate;
    }

    public JianYingProjectMeta setTmDraftCreate(Long tmDraftCreate) {
        this.tmDraftCreate = tmDraftCreate;
        return this;
    }

    public Long getTmDraftModified() {
        return tmDraftModified;
    }

    public JianYingProjectMeta setTmDraftModified(Long tmDraftModified) {
        this.tmDraftModified = tmDraftModified;
        return this;
    }

    public Integer getTmDraftRemoved() {
        return tmDraftRemoved;
    }

    public JianYingProjectMeta setTmDraftRemoved(Integer tmDraftRemoved) {
        this.tmDraftRemoved = tmDraftRemoved;
        return this;
    }

    public Integer getTmDuration() {
        return tmDuration;
    }

    public JianYingProjectMeta setTmDuration(Integer tmDuration) {
        this.tmDuration = tmDuration;
        return this;
    }
}
