package io.horizontalsystems.bankwallet.modules.transactions

import io.horizontalsystems.bankwallet.entities.Coin
import io.horizontalsystems.bankwallet.entities.TransactionItem
import io.horizontalsystems.bankwallet.entities.TransactionRecord
import io.horizontalsystems.bankwallet.entities.Wallet
import java.util.concurrent.CopyOnWriteArrayList

class TransactionItemDataSource {
    val count
        get() = items.size

    private val items = CopyOnWriteArrayList<TransactionItem>()

    fun clear() {
        items.clear()
    }

    fun add(items: List<TransactionItem>) {
        this.items.addAll(items)
    }

    fun itemForIndex(index: Int): TransactionItem = items[index]

    fun itemIndexesForTimestamp(coin: Coin, timestamp: Long): List<Int> {
        val indexes = mutableListOf<Int>()

        items.forEachIndexed { index, transactionItem ->
            if (transactionItem.wallet.coin == coin && transactionItem.record.timestamp == timestamp) {
                indexes.add(index)
            }
        }

        return indexes
    }

    fun itemIndexesForPending(wallet: Wallet, thresholdBlockHeight: Int): List<Int> {
        val indexes = mutableListOf<Int>()

        items.forEachIndexed { index, item ->
            if (item.wallet == wallet && (item.record.blockHeight == null || item.record.blockHeight >= thresholdBlockHeight)) {
                indexes.add(index)
            }
        }

        return indexes
    }

    fun handleModifiedItems(updatedItems: List<TransactionItem>, insertedItems: List<TransactionItem>) {
        val tmp = items.toMutableSet()
        tmp.addAll(updatedItems)
        tmp.addAll(insertedItems)

        items.clear()
        items.addAll(tmp.sortedDescending())
    }

    fun shouldInsertRecord(record: TransactionRecord): Boolean {
        return items.lastOrNull()?.record?.let { it < record } ?: true
    }
}
