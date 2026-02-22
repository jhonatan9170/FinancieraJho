package com.example.financierajho.UI.Home.Benefits
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.financierajho.databinding.FragmentBenefitsBinding

class BenefitsFragment : Fragment() {
    var _binding: FragmentBenefitsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBenefitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl("https://ohbeneficios.tarjetaoh.pe/")
    }

}