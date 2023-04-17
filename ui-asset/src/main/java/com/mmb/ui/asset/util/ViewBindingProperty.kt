package com.mmb.ui.asset.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingProperty<T : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: (View) -> T,
    private val beforeDestroyCallback: (T.() -> Unit)?,
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleEventObserver {

            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    binding?.let { beforeDestroyCallback?.invoke(it) }
                    binding = null
                }
            }

        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (binding != null) {
            return binding!!
        }

        if (!fragment.viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException(
                "Should not attempt to get bindings when Fragment views are destroyed."
            )
        }
        return viewBindingFactory(thisRef.requireView()).also { binding = it }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T,
    beforeDestroyCallback: (T.() -> Unit)? = null,
) = ViewBindingProperty(this, viewBindingFactory, beforeDestroyCallback)