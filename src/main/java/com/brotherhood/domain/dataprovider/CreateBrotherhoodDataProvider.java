package com.brotherhood.domain.dataprovider;

import com.brotherhood.domain.entity.BrotherhoodEntity;

public interface CreateBrotherhoodDataProvider {
    BrotherhoodEntity save(BrotherhoodEntity brotherhood);
}