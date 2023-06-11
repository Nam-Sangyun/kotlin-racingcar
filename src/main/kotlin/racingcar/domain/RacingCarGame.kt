package racingcar.domain

class RacingCarGame {

    fun progressGame(totalRound: Long, cars: List<Car>): List<RacingCarRoundResult> {
        validate(totalRound, cars)

        return (START_ROUND..totalRound)
            .map { round ->
                val roundResult = progressRound(cars)
                RacingCarRoundResult(round, roundResult)
            }
    }

    private fun validate(totalRound: Long, cars: List<Car>) {
        require(totalRound >= 1) { "시도 횟수는 1 이상이어야 합니다." }
        require(cars.isNotEmpty()) { "자동차 대수는 1 이상이어야 합니다." }

        val existsExceedNameLength = cars.any { it.name.length > 5 }
        require(!existsExceedNameLength) { "자동차 이름은 5자를 초과할 수 없습니다." }
    }

    private fun progressRound(cars: List<Car>): List<RacingCarDriveResult> {
        return cars.map { car ->
            car.drive()
            RacingCarDriveResult(car.carNumber, car.position, car.name)
        }
    }

    companion object {
        const val MIN_RANDOM_DRIVE_NUMBER: Long = 4
        const val RANDOM_NUMBER_BASE: Long = 0
        const val RANDOM_NUMBER_LIMIT: Long = 9
        const val START_ROUND = 1
    }
}
