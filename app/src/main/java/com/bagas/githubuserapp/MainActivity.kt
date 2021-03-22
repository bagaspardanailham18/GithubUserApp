package com.bagas.githubuserapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataRepo: Array<String>
    private lateinit var dataAvatar: TypedArray

    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        prepare()
        addItem()

        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(users)
        rvUser.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                moveActivity(data)
            }
        })
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataRepo = resources.getStringArray(R.array.repository)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                    dataUsername[position],
                    dataName[position],
                    dataLocation[position],
                    dataRepo[position].toInt(),
                    dataCompany[position],
                    dataFollowers[position].toInt(),
                    dataFollowing[position].toInt(),
                    dataAvatar.getResourceId(position, -1)
            )
            users.add(user)
        }
    }

    private fun moveActivity(data: User) {
        val intent = Intent(this@MainActivity, DetailUserActivity::class.java).apply {
            putExtra(DetailUserActivity.EXTRA_USER, data)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.menu_search -> {
                showRecyclerList()
            }
        }
    }
}