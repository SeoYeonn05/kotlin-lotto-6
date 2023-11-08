package lotto.view

import lotto.model.*
import lotto.util.inputHandler

class LottoSystemView {
    fun getLottoPurchaseMount(): String {
        println(ENTER_LOTTO_PURCHASE_AMOUNT_MESSAGE)
        return inputHandler()
    }

    fun getWinningLottoNum(): String {
        println(REQUEST_WINNING_LOTTO_NUM_MESSAGE)
        return inputHandler()
    }

    fun getBonusLottoNum(): String {
        println(REQUEST_BONUS_LOTTO_NUM_MESSAGE)
        return inputHandler()
    }

    fun printLottoNumList(lottoNumList: LottoPaper) {
        println("${lottoNumList.getLottoPaper().size}$PURCHASED_LOTTO_COUNT_MESSAGE")
        for (lottoNums in lottoNumList.getLottoPaper()) {
            println(lottoNums)
        }
    }

    fun printWinningStatistics(lottoResult: LottoResult) {
        var lottoMatchNum = LottoMatchNum.values()

        println(WINNING_STATISTICS_MESSAGE)
        println(DIVIDER_LINE)

        lottoMatchNum.forEach { matchNum ->
            if (matchNum == LottoMatchNum.ERROR || matchNum == LottoMatchNum.EXTRA) {
                return@forEach
            }
            printMatchingPrizeByNumber(lottoResult, matchNum)
        }
    }


    private fun printMatchingPrizeByNumber(lottoResult: LottoResult, matchNum: LottoMatchNum) {
        var matchNumCount = lottoResult.getMatchingLottoResult()

        if (matchNum == LottoMatchNum.FIVE_PLUS_BONUS) {
            print("${matchNum.matchingNum}$COINCIDE_NUM_MESSAGE, $COINCIDE_BONUS_NUM_MESSAGE ${printLottoPrizes(matchNum)} - ")
            println("${matchNumCount[matchNum]}개")
        } else {
            print("${matchNum.matchingNum}$COINCIDE_NUM_MESSAGE ${printLottoPrizes(matchNum)} - ")
            println("${matchNumCount[matchNum]}개")
        }
    }

    private fun printLottoPrizes(matchNum: LottoMatchNum): String {
        var matchNumPrize = LottoPrize().winningsPrizeMap[matchNum]?.let {
            formatNumberWithThousandSeparators(
                it
            )
        }
        return WINNING_INFO_MESSAGE.replace("winningPrice", "$matchNumPrize")
    }

    private fun formatNumberWithThousandSeparators(amount: Int): String {
        return String.format("%,d", amount)
    }

    fun printRateOfReturn(profitPercentage: Double) {
        println(RATE_OF_RETURN_MESSAGE.replace("profitPercentage", "$profitPercentage"))
    }

    companion object {
        const val ENTER_LOTTO_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해주세요."
        const val PURCHASED_LOTTO_COUNT_MESSAGE = "개를 구매했습니다."
        const val REQUEST_WINNING_LOTTO_NUM_MESSAGE = "당첨 번호를 입력해 주세요."
        const val REQUEST_BONUS_LOTTO_NUM_MESSAGE = "보너스 번호를 입력해 주세요."
        const val WINNING_STATISTICS_MESSAGE = "당첨 통계"
        const val COINCIDE_NUM_MESSAGE = "개 일치"
        const val COINCIDE_BONUS_NUM_MESSAGE = "보너스 볼 일치"
        const val WINNING_INFO_MESSAGE = "(winningPrice원)"
        const val DIVIDER_LINE = "---"
        const val RATE_OF_RETURN_MESSAGE = "총 수익률은 profitPercentage%입니다"

    }
}