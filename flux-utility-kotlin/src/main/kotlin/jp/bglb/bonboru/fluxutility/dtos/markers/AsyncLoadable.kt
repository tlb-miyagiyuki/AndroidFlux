package jp.bglb.bonboru.fluxutility.dtos.markers

import jp.bglb.bonboru.fluxutility.types.RequestStatus

/**
 * Created by Tetsuya Masuda on 2016/07/01.
 */
interface AsyncLoadable {
  fun updateRequestStatus(status: RequestStatus)
}
