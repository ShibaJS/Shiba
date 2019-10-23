package org.shibajs.shiba.type

import android.os.Build
import androidx.annotation.RequiresApi
import org.shibajs.shiba.Event
import java.util.function.Predicate
//
class ShibaArray : ArrayList<Any?>() {
    var collectionChanged: Event<CollectionChangedEventArg> = Event()

    override fun add(element: Any?): Boolean {
        val result = super.add(element)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
        }
        return result
    }

    override fun add(index: Int, element: Any?) {
        super.add(index, element)
        collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
    }

    override fun addAll(elements: Collection<Any?>): Boolean {
        val result = super.addAll(elements)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
        }
        return result
    }

    override fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        val result = super.addAll(index, elements)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
        }
        return result
    }

    override fun clear() {
        super.clear()
        collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
    }

    override fun remove(element: Any?): Boolean {
        val result = super.remove(element)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    override fun removeAll(elements: Collection<Any?>): Boolean {
        val result = super.removeAll(elements)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    override fun removeAt(index: Int): Any? {
        val result = super.removeAt(index)
        if (result != null) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun removeIf(filter: Predicate<in Any?>): Boolean {
        val result = super.removeIf(filter)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
    }
}