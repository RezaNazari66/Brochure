package com.bonial.brochurelist

import com.bonial.domain.models.Brochure
import com.bonial.domain.models.ContentTypeEnum

object MockedBrochures {

    val brochure = Brochure(
        image = "test image",
        retailerName = "test name",
        contentType = ContentTypeEnum.BROCHURE,
        distance = 1.0
    )
    val premiumBrochure = Brochure(
        image = "test image",
        retailerName = "test name",
        contentType = ContentTypeEnum.BROCHURE_PREMIUM,
        distance = 1.0
    )
    val otherBrochure = Brochure(
        image = "test image",
        retailerName = "test name",
        contentType = ContentTypeEnum.OTHER,
        distance = 1.0
    )

    val brochureLongDistance = brochure.copy(distance = 10.0)
    val premiumBrochureLongDistance = premiumBrochure.copy(distance = 10.0)
    val otherBrochureLongDistance = otherBrochure.copy(distance = 10.0)
}