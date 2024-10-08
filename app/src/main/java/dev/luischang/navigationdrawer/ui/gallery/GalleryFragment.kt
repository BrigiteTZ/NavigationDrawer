package dev.luischang.navigationdrawer.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dev.luischang.navigationdrawer.databinding.FragmentGalleryBinding
import dev.luischang.navigationdrawer.ui.shared.HomeGalleryViewModel

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val homeGalleryViewModel:HomeGalleryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val wvPage:WebView=binding.wvPage
        wvPage.settings.javaScriptEnabled=true

        wvPage.webViewClient=object:WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url:String
            ): Boolean {
                if (url !=null)
                view?.loadUrl(url)
                return true
            }
        }

        homeGalleryViewModel.url.observe(viewLifecycleOwner){url ->
            wvPage.loadUrl(url)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}