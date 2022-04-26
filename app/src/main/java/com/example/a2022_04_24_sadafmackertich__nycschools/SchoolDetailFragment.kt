package com.example.a2022_04_24_sadafmackertich__nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.ApiCalls
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.SchoolSATDataObject
import com.example.a2022_04_24_sadafmackertich__nycschools.databinding.SchoolDetailFragmentBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolDetailFragment : Fragment() {

    private lateinit var viewModel: SchoolDetailViewModel
    private lateinit var mBinding: SchoolDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SchoolDetailFragmentBinding.inflate(
            inflater, container, false
        )

        return mBinding.root
    }

    val args: SchoolDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbn = args.dbn

        val apiInterface = ApiCalls.create().getSatScores(dbn)

        apiInterface.enqueue(object : Callback<SchoolSATDataObject> {
            override fun onResponse(
                call: Call<SchoolSATDataObject>,
                response: Response<SchoolSATDataObject>
            ) {
                if (response.body() != null) {
                    val data = response.body()!!

                    requireActivity().actionBar!!.title = data.school_name

                    mBinding.writing.text = data.sat_writing_avg_score.toString()
                    mBinding.math.text = data.sat_math_avg_score.toString()
                    mBinding.reading.text = data.sat_critical_reading_avg_score.toString()

                }

            }

            override fun onFailure(call: Call<SchoolSATDataObject>, t: Throwable) {
                Snackbar.make(
                    mBinding.root,
                    "Error, unable to find schools",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        })

    }
}


