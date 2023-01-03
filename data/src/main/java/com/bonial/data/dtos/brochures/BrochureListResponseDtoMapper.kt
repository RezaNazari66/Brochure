package com.bonial.data.dtos.brochures

import com.bonial.domain.models.Brochure
import com.bonial.domain.models.BrochureListResponse
import com.bonial.data.base.DtoMapper
import com.bonial.domain.models.ContentTypeEnum
import javax.inject.Inject

class BrochureListResponseDtoMapper @Inject constructor() :
    DtoMapper<BrochureListResponseDto, BrochureListResponse> {

    override fun mapToDataModel(dto: BrochureListResponseDto?): BrochureListResponse {
        return BrochureListResponse(
            brochureList = dto?.embedded?.contents?.map {
                Brochure(
                    image = it.innerContent?.brochureImage.orEmpty(),
                    retailerName = it.innerContent?.retailer?.name.orEmpty(),
                    contentType = mapContentType(it.contentType)
                )
            },
        )
    }

    private fun mapContentType(contentTypeDto: ContentTypeDto?): ContentTypeEnum {
        return when (contentTypeDto) {
            ContentTypeDto.BROCHURE -> ContentTypeEnum.BROCHURE
            ContentTypeDto.BROCHURE_PREMIUM -> ContentTypeEnum.BROCHURE_PREMIUM
            else -> ContentTypeEnum.OTHER
        }

    }
}