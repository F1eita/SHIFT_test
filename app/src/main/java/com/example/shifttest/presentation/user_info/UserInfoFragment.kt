package com.example.shifttest.presentation.user_info

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.shifttest.R
import com.example.shifttest.databinding.FragmentUserInfoBinding
import dagger.android.support.AndroidSupportInjection
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject


class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private val args: UserInfoFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        InfoViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        viewModel.getUser(args.id)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private fun addObserver(){
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            Glide.with(binding.imageView)
                .load(user.picture)
                .transform(
                    MultiTransformation(
                        CenterCrop(),
                        CircleCrop()
                    )
                ).into(binding.imageView)
            with(binding){
                tvName.text = resources.getString(R.string.name_pattern, user.firstName,
                    user.lastName)
                tvEmail.text = resources.getString(R.string.email_pattern, user.email)
                tvGender.text = resources.getString(R.string.gender_pattern, user.gender)
                tvPhone.text = resources.getString(R.string.phone_pattern, user.phone)
                tvDob.text = resources.getString(R.string.dob_pattern, user.age.toString(),
                    convertDateToView(user.dob))
                tvAddress.text = resources.getString(R.string.address_pattern, user.country,
                    user.state, user.country, user.streetName, user.streetNumber.toString())
            }
        }
    }

    private fun convertDateToView(inputDateString: String): String{
        val dateFormat = SimpleDateFormat("", Locale.getDefault())
        val inputFormat = dateFormat.apply{
            applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val date = inputFormat.parse(inputDateString)
        val outputFormat = dateFormat.apply{
            applyPattern("dd.MM.yy")
            timeZone = TimeZone.getDefault()
        }
        return outputFormat.format(date)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}