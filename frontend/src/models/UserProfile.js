import generateUUID8 from "../helpers/Helper";

class UserProfile {
  constructor(name) {
    if(name !== undefined || name !== null){
      this.userNickname = name;
    }
    else{
      this.userNickname = "guest"+generateUUID8();
    }
  }
}

export default UserProfile