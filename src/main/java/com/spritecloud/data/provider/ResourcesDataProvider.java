package com.spritecloud.data.provider;

import static com.spritecloud.data.changeless.ResourcesErrorsData.ERRORS_PAGES_GREATER;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ResourcesDataProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    var recordsMAXPageSize = Integer.MAX_VALUE;
    var recordsMaxPageSizeNumberTwo = Integer.MAX_VALUE;
    // a list of what it should be all combinations:
    //        var recordsMinPageSize = 0;
    //        var recordsLessThanMinInstallments = -1;
    //        var recordsExceedPageSize = above Integer.MAX_VALUE;
    //        var recordsWithNotValidPage = unique character @ or null
    //        var recordsWithEmptyPage = empty String " "

    return Stream.of(
        arguments(recordsMAXPageSize, ERRORS_PAGES_GREATER.key, ERRORS_PAGES_GREATER.message),
        arguments(
            recordsMaxPageSizeNumberTwo, ERRORS_PAGES_GREATER.key, ERRORS_PAGES_GREATER.message));
  }
}