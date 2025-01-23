package com.winphyoethu.pocketo.common.result

sealed interface ErrorCode

/**
 * Error codes for Account Repository, start with 10-1,2,3 etc.
 */
sealed class AccountErrorCode(val errorCode: Int, var message: String? = null) : ErrorCode {
    /**
     * Account Create Fail Error Code - 101
     */
    data object AccountCreateFail : AccountErrorCode(101)

    /**
     * Account Create Fail Error Code - 102
     */
    data object AccountUpdateFail : AccountErrorCode(102)

    /**
     * Account Delete Fail Error Code - 103
     */
    data object AccountDeleteFail : AccountErrorCode(103)

    /**
     * Account Create Fail Error Code - 104
     */
    data object AccountRetrieveFail : AccountErrorCode(104)

    override fun toString(): String {
        return "${javaClass.name} :: $errorCode - $message"
    }
}

/**
 * Error codes for Category Repository, start with 20-1,2,3 etc.
 */
sealed class CategoryErrorCode(val errorCode: Int, var message: String? = null) : ErrorCode {
    /**
     * Category Create Fail Error Code - 201
     */
    data object CategoryCreateFail : CategoryErrorCode(201)

    /**
     * Category Update Fail Error Code - 202
     */
    data object CategoryUpdateFail : CategoryErrorCode(202)

    /**
     * Category Retrieve Fail Error Code - 203
     */
    data object CategoryRetrieveFail : CategoryErrorCode(203)

    override fun toString(): String {
        return "${javaClass.name} :: $errorCode - $message"
    }
}

/**
 * Error codes for Expense Repository, start with 30-1,2,3 etc.
 */
sealed class ExpenseErrorCode(val errorCode: Int, var message: String? = null) : ErrorCode {
    /**
     * Expense Create Fail Error Code - 301
     */
    data object ExpenseCreateFail : ExpenseErrorCode(301)

    /**
     * Expense Update Fail Error Code - 302
     */
    data object ExpenseUpdateFail : ExpenseErrorCode(302)

    /**
     * Expense Retrieve Fail Error Code - 303
     */
    data object ExpenseDeleteFail : ExpenseErrorCode(303)

    /**
     * Expense Retrieve Fail Error Code - 304
     */
    data object ExpenseRetrieveFail : ExpenseErrorCode(304)

    override fun toString(): String {
        return "${javaClass.name} :: $errorCode - $message"
    }
}

/**
 * Error codes for Expense Repository, start with 40-1,2,3 etc.
 */
sealed class MonthlyExpenseErrorCode(val errorCode: Int, var message: String? = null) : ErrorCode {
    /**
     * Monthly Expense Create Fail Error Code - 401
     */
    data object MonthlyExpenseCreateFail : ExpenseErrorCode(401)

    /**
     * Monthly Expense Update Fail Error Code - 402
     */
    data object MonthlyExpenseUpdateFail : ExpenseErrorCode(402)

    /**
     * Monthly Expense Retrieve Fail Error Code - 403
     */
    data object MonthlyExpenseRetrieveFail : ExpenseErrorCode(403)

    override fun toString(): String {
        return "${javaClass.name} :: $errorCode - $message"
    }
}

/**
 * Error codes for Currency Repository, start with 50-1,2,3 etc.
 */
sealed class CurrencyErrorCode(val errorCode: Int, var message: String? = null): ErrorCode {
    /**
     * Currency Retrieve Fail Error Code - 501
     */
    data object CurrencyRetrieveFail : CurrencyErrorCode(401)
}