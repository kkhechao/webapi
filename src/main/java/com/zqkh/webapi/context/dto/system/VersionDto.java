package com.zqkh.webapi.context.dto.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VersionDto {
    private  String intro;
    private  String latestVersion;
    private  Boolean mandatoryUpdate;
    private  int versionCode;
    private  String versionName;
    private  String url;
}
