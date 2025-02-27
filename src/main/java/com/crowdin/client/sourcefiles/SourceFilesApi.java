package com.crowdin.client.sourcefiles;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.sourcefiles.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SourceFilesApi extends CrowdinApi {
    public SourceFilesApi(Credentials credentials) {
        super(credentials);
    }

    public SourceFilesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param name      filter by branch name
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of branches
     */
    public ResponseList<Branch> listBranches(Long projectId, String name, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "name", Optional.ofNullable(name),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        BranchResponseList branchResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/branches", new HttpRequestConfig(queryParams), BranchResponseList.class);
        return BranchResponseList.to(branchResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created branch
     */
    public ResponseObject<Branch> addBranch(Long projectId, AddBranchRequest request) throws HttpException, HttpBadRequestException {
        BranchResponseObject branchResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/branches", request, new HttpRequestConfig(), BranchResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     * @return branch
     */
    public ResponseObject<Branch> getBranch(Long projectId, Long branchId) throws HttpException, HttpBadRequestException {
        BranchResponseObject branchResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId, new HttpRequestConfig(), BranchResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     */
    public void deleteBranch(Long projectId, Long branchId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/branches/" + branchId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param branchId  branch identifier
     * @param request   request object
     * @return updated branch
     */
    public ResponseObject<Branch> editBranch(Long projectId, Long branchId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        BranchResponseObject branchResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/branches/" + branchId, request, new HttpRequestConfig(), BranchResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId   project identifier
     * @param branchId    filter by branch id
     * @param directoryId filter by directory id
     * @param filter      filter directories by name
     * @param recursion   use to list directories recursively
     * @param limit       maximum number of items to retrieve (default 25)
     * @param offset      starting offset in the collection (default 0)
     * @return list of directories
     */
    public ResponseList<Directory> listDirectories(Long projectId, Long branchId, Long directoryId, String filter, Object recursion, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "branchId", Optional.ofNullable(branchId),
                "directoryId", Optional.ofNullable(directoryId),
                "filter", Optional.ofNullable(filter),
                "recursion", Optional.ofNullable(recursion),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        DirectoryResponseList directoryResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/directories", new HttpRequestConfig(queryParams), DirectoryResponseList.class);
        return DirectoryResponseList.to(directoryResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created directory
     */
    public ResponseObject<Directory> addDirectory(Long projectId, AddDirectoryRequest request) throws HttpException, HttpBadRequestException {
        DirectoryResponseObject post = this.httpClient.post(this.url + "/projects/" + projectId + "/directories", request, new HttpRequestConfig(), DirectoryResponseObject.class);
        return ResponseObject.of(post.getData());
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     * @return directory
     */
    public ResponseObject<Directory> getDirectory(Long projectId, Long directoryId) throws HttpException, HttpBadRequestException {
        DirectoryResponseObject directoryResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/directories/" + directoryId, new HttpRequestConfig(), DirectoryResponseObject.class);
        return ResponseObject.of(directoryResponseObject.getData());
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     */
    public void deleteDirectory(Long projectId, Long directoryId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/directories/" + directoryId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId   project identifier
     * @param directoryId directory identifier
     * @param request     request object
     * @return updated directory
     */
    public ResponseObject<Directory> editDirectory(Long projectId, Long directoryId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        DirectoryResponseObject directoryResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/directories/" + directoryId, request, new HttpRequestConfig(), DirectoryResponseObject.class);
        return ResponseObject.of(directoryResponseObject.getData());
    }

    /**
     * @param projectId   project identifier
     * @param branchId    filter by branch id
     * @param directoryId filter by directory id
     * @param filter      filter files by name
     * @param recursion   use to list directories recursively
     * @param limit       maximum number of items to retrieve (default 25)
     * @param offset      starting offset in the collection (default 0)
     * @return list of files
     */
    public ResponseList<? extends FileInfo> listFiles(Long projectId, Long branchId, Long directoryId, String filter, Object recursion, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "branchId", Optional.ofNullable(branchId),
                "directoryId", Optional.ofNullable(directoryId),
                "filter", Optional.ofNullable(filter),
                "recursion", Optional.ofNullable(recursion),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileInfoResponseList fileInfoResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/files", new HttpRequestConfig(queryParams), FileInfoResponseList.class);
        return FileInfoResponseList.to(fileInfoResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created file
     */
    public ResponseObject<? extends FileInfo> addFile(Long projectId, AddFileRequest request) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/files", request, new HttpRequestConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @return file
     */
    public ResponseObject<? extends FileInfo> getFile(Long projectId, Long fileId) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId, new HttpRequestConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param request   request object
     * @return updated file
     */
    public ResponseObject<? extends FileInfo> updateOrRestoreFile(Long projectId, Long fileId, UpdateOrRestoreFileRequest request) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.put(this.url + "/projects/" + projectId + "/files/" + fileId, request, new HttpRequestConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     */
    public void deleteFile(Long projectId, Long fileId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/files/" + fileId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param request   request object
     * @return updated file
     */
    public ResponseObject<? extends FileInfo> editFile(Long projectId, Long fileId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        FileResponseObject fileResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/files/" + fileId, request, new HttpRequestConfig(), FileResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @return file download link
     */
    public ResponseObject<DownloadLink> downloadFile(Long projectId, Long fileId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/download", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of file revisions
     */
    public ResponseList<FileRevision> listFileRevisions(Long projectId, Long fileId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileRevisionResponseList fileRevisionResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions", new HttpRequestConfig(queryParams), FileRevisionResponseList.class);
        return FileRevisionResponseList.to(fileRevisionResponseList);
    }

    /**
     * @param projectId  project identifier
     * @param fileId     file identifier
     * @param revisionId revision identifier
     * @return file revision
     */
    public ResponseObject<FileRevision> getFileRevision(Long projectId, Long fileId, Long revisionId) throws HttpException, HttpBadRequestException {
        FileRevisionResponseObject fileResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/files/" + fileId + "/revisions/" + revisionId, new HttpRequestConfig(), FileRevisionResponseObject.class);
        return ResponseObject.of(fileResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of reviewed strings build statuses
     */
    public ResponseList<ReviewedStringsBuild> listReviewedSourceFilesBuilds(Long projectId, Long branchId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/strings/reviewed-builds", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "branchId", Optional.ofNullable(branchId),
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset)
        );
        ReviewedStringBuildResponseList reviewedStringBuildResponseList = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ReviewedStringBuildResponseList.class);
        return ReviewedStringBuildResponseList.to(reviewedStringBuildResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return reviewed strings build status
     */
    public ResponseObject<ReviewedStringsBuild> buildReviewedSourceFiles(Long projectId, BuildReviewedSourceFilesRequest request) {
        String builtUrl = String.format("%s/projects/%d/strings/reviewed-builds", this.url, projectId);
        ReviewedStringBuildResponseObject reviewedStringBuildResponseObject = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ReviewedStringBuildResponseObject.class);
        return ResponseObject.of(reviewedStringBuildResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param buildId build identifier
     * @return reviewed strings build status
     */
    public ResponseObject<ReviewedStringsBuild> checkReviewedSourceFilesBuildStatus(Long projectId, Long buildId) {
        String builtUrl = String.format("%s/projects/%d/strings/reviewed-builds/%d", this.url, projectId, buildId);
        ReviewedStringBuildResponseObject reviewedStringBuildResponseObject = this.httpClient.get(builtUrl, new HttpRequestConfig(), ReviewedStringBuildResponseObject.class);
        return ResponseObject.of(reviewedStringBuildResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param buildId build identifier
     * @return download link
     */
    public ResponseObject<DownloadLink> downloadReviewedSourceFiles(Long projectId, Long buildId) {
        String builtUrl = String.format("%s/projects/%d/strings/reviewed-builds/%d/download", this.url, projectId, buildId);
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(builtUrl, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }
}
