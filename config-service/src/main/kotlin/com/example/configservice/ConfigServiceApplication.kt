package com.example.configservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigServiceApplication

fun main(args: Array<String>) {
	runApplication<ConfigServiceApplication>(*args)
}
class UnionFind(sentence:List<List<String>>){

	val root:HashMap<String, String> by lazy {
		val map =hashMapOf<String,String>()
		for((a,b) in sentence){map.putIfAbsent(a,a);map.putIfAbsent(b,b)}
		map
	}
	val rank:HashMap<String, Int> by lazy {
		val map =hashMapOf<String, Int>()
		root.keys.forEach{key-> map.put(key,1)}
		map
	}

	//node가 String인 경우


	fun union(x:String, y:String):Boolean{
		val rootX = find(x)
		val rootY = find(y)
		if(rootX!=rootY){
			when{
				rank[rootX]!!>rank[rootY]!! -> root[rootY] = rootX
				rank[rootX]!!<rank[rootY]!! -> root[rootX] = rootY
				else->{
					root[rootX] = rootY
					rank.put(rootY, rank.getOrDefault(rootY,1)+1)
				}
			}
			return true
		}
		return false
	}
	fun find(word:String):String{
		if(word==root[word])return word
		root[word] = find(root[word]!!)
		return root[word]!!
	}
}