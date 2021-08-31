package fr.bilog.emuserefontebackend.models;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FcmIdRequest {

    private String fcmId;
    @ApiParam(name = "deviceType", value = "Android = 1, iOS = 2")
    private Integer deviceType;
}
