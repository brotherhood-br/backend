package com.brotherhood.domain.dataprovider;

import java.time.LocalDate;
import java.util.UUID;

public interface GetBrotherhoodViewCountBeforeDateDataProvider {
    Integer getViewCount(LocalDate date, UUID brotherhoodId);
}
