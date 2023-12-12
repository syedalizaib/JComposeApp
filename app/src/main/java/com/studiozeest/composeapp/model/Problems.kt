package com.studiozeest.composeapp.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections

@Entity
@Immutable
data class Problems(
  @PrimaryKey val id: Long,
  val diseaseName : String,
  @field:TypeConverters(DiseaseInfoTypeConverter::class)
  val diseaseInfo : List<DiseaseInfo>
){
  data class AssociatedDrug (
    val name     : String,
    val dose     : String,
    val strength : String
  ){
    companion object {
      fun mock() = AssociatedDrug(
        name = "name",
        dose = "100mg",
        strength = "100mg"
      )
    }
    override fun toString(): String {
      return "$name-$dose-$strength"
    }
  }
  data class MedicationsClasses (
    val id : Int,
    val medicationsClassName : String,
    @field:TypeConverters(AssociatedDrugTypeConverter::class)
    val associatedDrug : List<AssociatedDrug>
  )
  {
    companion object {
      fun mock() = MedicationsClasses(
        id = 0,
        medicationsClassName = "c1",
        associatedDrug = ArrayList()
      )
    }
    override fun toString(): String {
      return "$medicationsClassName-$associatedDrug"
    }
  }
  data class DiseaseInfo (
    @field:TypeConverters(MedicationsTypeConverter::class)
    val medications : List<Medications>,
    val labs : List<Labs>
  ){
    companion object {
      fun mock() = DiseaseInfo(
        medications = ArrayList(),
        labs = ArrayList()
      )
    }

    override fun toString(): String {
      return "$medications-$labs"
    }
  }

  data class Labs (
    val missing_field : String
  ){
    companion object {
      fun mock() = Labs(
        missing_field = "missing"
      )
    }
    override fun toString(): String {
      return missing_field
    }
  }
  data class Medications (
    @field:TypeConverters(MedicationsClassesTypeConverter::class)
    val medicationsClasses : List<MedicationsClasses>
  ){
    companion object {
      fun mock() = Medications(
        medicationsClasses = ArrayList()
      )
    }
    override fun toString(): String {
      return "$medicationsClasses"
    }
  }
  companion object {
    fun mock() = Problems(
      id = 0,
      diseaseName = "D1",
      diseaseInfo = ArrayList()
    )
  }
  class DiseaseInfoTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<DiseaseInfo> {
      if (data == null) {
        return Collections.emptyList()
      }

      val listType = object : TypeToken<List<DiseaseInfo>>() {

      }.type

      return gson.fromJson<List<DiseaseInfo>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<DiseaseInfo>): String {
      return gson.toJson(someObjects)
    }
  }
  class AssociatedDrugTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<AssociatedDrug> {
      if (data == null) {
        return Collections.emptyList()
      }

      val listType = object : TypeToken<List<AssociatedDrug>>() {

      }.type

      return gson.fromJson<List<AssociatedDrug>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<AssociatedDrug>): String {
      return gson.toJson(someObjects)
    }
  }
  class MedicationsTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Medications> {
      if (data == null) {
        return Collections.emptyList()
      }

      val listType = object : TypeToken<List<Medications>>() {

      }.type

      return gson.fromJson<List<Medications>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Medications>): String {
      return gson.toJson(someObjects)
    }
  }
  class MedicationsClassesTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<MedicationsClasses> {
      if (data == null) {
        return Collections.emptyList()
      }

      val listType = object : TypeToken<List<MedicationsClasses>>() {

      }.type

      return gson.fromJson<List<MedicationsClasses>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<MedicationsClasses>): String {
      return gson.toJson(someObjects)
    }
  }
}
