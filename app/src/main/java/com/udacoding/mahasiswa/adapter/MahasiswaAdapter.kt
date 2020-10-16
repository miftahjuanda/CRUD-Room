package com.udacoding.mahasiswa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.db.mahasiswa.Mahasiswa
import kotlinx.android.synthetic.main.list_item.view.*

class MahasiswaAdapter(
    private val data: List<Mahasiswa>,
    private val itemClick: Onclick
) : RecyclerView.Adapter<MahasiswaAdapter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return viewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = data.get(position)

        holder.bind(item)

    }

    override fun getItemCount(): Int = data.size ?: 0

    class viewHolder(val view: View, val itemClick: Onclick) : RecyclerView.ViewHolder(view) {

        fun bind(item: Mahasiswa?) {
            view.tv_nama_list.text = item?.nama
            view.tv_nim_list.text = item?.nim
            view.tv_jurusan_list.text = item?.jurusan
            view.tv_alamat_list.text = item?.alamat

            view.setOnClickListener { itemClick.update(item) }

            view.btn_delete_list.setOnClickListener { itemClick.delete(item) }
        }
    }

    interface Onclick {
        fun update(item: Mahasiswa?)
        fun delete(item: Mahasiswa?)
    }
}