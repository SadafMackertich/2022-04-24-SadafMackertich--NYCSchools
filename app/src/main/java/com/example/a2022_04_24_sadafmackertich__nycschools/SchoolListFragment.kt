package com.example.a2022_04_24_sadafmackertich__nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.ApiCalls
import com.example.a2022_04_24_sadafmackertich__nycschools.Network.SchoolListDataObject
import com.example.a2022_04_24_sadafmackertich__nycschools.databinding.FragmentSchoolListBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolListFragment : Fragment() {
    private lateinit var mBinding: FragmentSchoolListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSchoolListBinding.inflate(
            layoutInflater,
            container, false
        )

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        requireActivity().actionBar!!.title = "NYC Schools"

        val apiInterface = ApiCalls.create().getSchools()

        apiInterface.enqueue(object : Callback<List<SchoolListDataObject>> {
            override fun onResponse(
                call: Call<List<SchoolListDataObject>>,
                response: Response<List<SchoolListDataObject>>
            ) {

                if (response.body() != null)
                    setData(response.body())
            }

            override fun onFailure(call: Call<List<SchoolListDataObject>>, t: Throwable) {
                Snackbar.make(mBinding.root,
                    "Error, school details not found",
                    Snackbar.LENGTH_INDEFINITE).show()
            }
        })
    }

    private fun setData(schools: List<SchoolListDataObject>?) {
        val adapter = SchoolRecyclerViewAdapter(schools)
        mBinding.list.adapter = adapter
    }


}








