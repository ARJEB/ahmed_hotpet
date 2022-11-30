const mongoose = require('mongoose')
const Schema = mongoose.Schema


const LikesSchema = new Schema({

    User_Swiper:{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'user',
    },
    
    User_Swiped:{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'user',
    },

    User_Swiper_Right:{
        type : Boolean,
        default:true
    },

    User_Swiped_Right:{

        type : Boolean,
        default:false
    },

    Match:{

        type : Boolean,
        default:false
    },


},{timestamps : true })

const Likes = mongoose.model('Likes',LikesSchema)
module.exports =Likes