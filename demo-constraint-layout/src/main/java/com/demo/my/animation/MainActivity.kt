package com.demo.my.animation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    // Views.
    val mLayout by lazy { findViewById<ConstraintLayout>(R.id.main) }
    val mBtnAnimate by lazy { findViewById<Button>(R.id.btn_animate) }
    val mWhateverView1 by lazy { findViewById<Button>(R.id.whatever1) }
    val mWhateverView2 by lazy { findViewById<Button>(R.id.whatever2) }

    // Animation.
    var mIsForwardAnimation: Boolean = true

    val mDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_fold)
    }

    override fun onResume() {
        super.onResume()

        mDisposables.add(
            RxView
                .clicks(mBtnAnimate)
                .debounce(250, TimeUnit.MILLISECONDS)
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
        set2.clone(this@MainActivity, R.layout.activity_main_expand)

        TransitionManager.beginDelayedTransition(mLayout)
        set2.applyTo(mLayout)
    }

    protected fun animateBackward() {
        val set1 = ConstraintSet()
        set1.clone(mLayout)

        val set2 = ConstraintSet()
        set2.clone(this@MainActivity, R.layout.activity_main_fold)

        TransitionManager.beginDelayedTransition(mLayout)
        set2.applyTo(mLayout)
    }
}
