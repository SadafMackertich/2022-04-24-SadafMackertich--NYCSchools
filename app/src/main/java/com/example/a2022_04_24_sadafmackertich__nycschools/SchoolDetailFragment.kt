package com.example.a2022_04_24_sadafmackertich__nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.ApiCalls
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.SchoolSATDataObject
import com.example.a2022_04_24_sadafmackertich__nycschools.databinding.SchoolDetailFragmentBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
* Shows the SAT scores
* */
class SchoolDetailFragment : Fragment() {

    private lateinit var viewModel: SchoolDetailViewModel
    private lateinit var mBinding: SchoolDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SchoolDetailFragmentBinding.inflate(
            inflater, container, false)

        return mBinding.root
    }

//    Get delegated Property
    val args: SchoolDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbn = args.dbn

        val apiInterface = ApiCalls.create().getSatScores(dbn)

        apiInterface.enqueue(object : Callback<List<SchoolSATDataObject>> {
            override fun onResponse(
                call: Call<List<SchoolSATDataObject>>,
                response: Response<List<SchoolSATDataObject>>
            ) {

                if (response.body() != null) {
                    val data = response.body()!!.get(0)

                    val truncated_name = data.school_name.subSequence(0, 25) + "..."

                    (requireActivity() as AppCompatActivity).supportActionBar!!
                        .title= truncated_name

                    mBinding.writing.text = "Writing: " + data.sat_writing_avg_score
                    mBinding.math.text = "Math: " + data.sat_math_avg_score
                    mBinding.reading.text = "Reading: " + data.sat_critical_reading_avg_score

                } else {

                    (requireActivity() as AppCompatActivity).supportActionBar!!
                        .title = "No Data"

                    mBinding.reading.text = "No data found"

                }

            }

            override fun onFailure(call: Call<List<SchoolSATDataObject>>, t: Throwable) {
                Snackbar.make(
                    mBinding.root,
                    "Error, unable to find schools",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        })

    }
}


