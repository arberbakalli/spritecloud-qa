package com.spritecloud.e2e;

import com.spritecloud.client.ResourcesClient;
import com.spritecloud.client.UsersClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.spritecloud.data.changeless.TestSuiteTags.E2E;
import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FullUsersAndResourcesE2ETest {

    private final UsersClient usersClient = new UsersClient();
    private final ResourcesClient resourcesClient = new ResourcesClient();

    @Test
    @Tag(E2E)
    @DisplayName("Should submit a successful simulation for a non-restricted CPF")
    void completeSimulation() {
        var notAuthorizedUsers = usersClient.queryUsersAndReturnUnAuthorized();
        assertThat(notAuthorizedUsers.getStatusCode()).isEqualTo(SC_UNSUPPORTED_MEDIA_TYPE);

        var notAuthorizedRecords = resourcesClient.queryRecordsAndReturnUnAuthorized();
        assertThat(notAuthorizedRecords.getStatusCode()).isEqualTo(SC_UNSUPPORTED_MEDIA_TYPE);
    }
}