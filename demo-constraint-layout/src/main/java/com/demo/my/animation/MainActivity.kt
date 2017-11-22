package com.demo.my.animation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.airbnb.epoxy.EpoxyController
import com.demo.my.animation.view.MyEpoxyController
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    // Views.
    val mLayout by lazy { findViewById<ConstraintLayout>(R.id.main) }
    val mBtnAnimate by lazy { findViewById<Button>(R.id.btn_animate) }
    val mTopView by lazy { findViewById<View>(R.id.top_view) }
    val mFooPicker by lazy { findViewById<RecyclerView>(R.id.foo_picker) }

    // Animation.
    var mIsForwardAnimation: Boolean = true

    var mBottomViewController: EpoxyController? = null

    // Observables.
    val mDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_folded)

        mBottomViewController = MyEpoxyController(15)
        mFooPicker.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mFooPicker.adapter = mBottomViewController?.adapter
    }

    override fun onDestroy() {
        super.onDestroy()

        mBottomViewController = null
    }

    override fun onResume() {
        super.onResume()

        mDisposables.add(
            RxView
                .clicks(mBtnAnimate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                               if (mIsForwardAnimation) {
                                   mIsForwardAnimation = false
                                   animateForward()
                               } else {
                                   mIsForwardAnimation = true
                                   animateBackward()
                               }
                           }))
    }

    override fun onPause() {
        super.onPause()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    protected fun animateForward() {
        val set1 = ConstraintSet()
        set1.clone(mLayout)

        val set2 = ConstraintSet()
        set2.clone(this@MainActivity, R.layout.activity_main_expanded)

        TransitionManager.beginDelayedTransition(mLayout)
        set2.applyTo(mLayout)
    }

    protected fun animateBackward() {
        val set1 = ConstraintSet()
        set1.clone(mLayout)

        val set2 = ConstraintSet()
        set2.clone(this@MainActivity, R.layout.activity_main_folded)

        TransitionManager.beginDelayedTransition(mLayout)
        set2.applyTo(mLayout)
    }
}
