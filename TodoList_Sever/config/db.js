const mongoose = require('mongoose')
mongoose.set('strictQuery', true)
const url_db = "mongodb://localhost:27017/Todo"

const connect = async() => {
    try {
        await mongoose.connect(url_db)
        console.log("Connect success")
    } catch (error) {
        console.log("error :"+ error)
        console.log('Connect failed')
    }
}

module.exports = {connect}