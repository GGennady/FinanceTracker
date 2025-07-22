package com.example.financetracker.data.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.financetracker.data.db.entities.AccountBriefEntity
import com.example.financetracker.data.db.entities.CategoryEntity
import com.example.financetracker.data.db.entities.TransactionEntity

data class TransactionWithAccountAndCategory(

    @Embedded
    val transaction: TransactionEntity,

    @Relation(parentColumn = "accountId", entityColumn = "id")
    val account: AccountBriefEntity,

    @Relation(parentColumn = "categoryId", entityColumn = "id")
    val category: CategoryEntity
)
