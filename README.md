# SocketVerse
A universe powered by WebSockets

## Requirements

### Backend
- Recommended Java SDK 21
- Recommended IDE [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/?section=windows)
### Frontend
- Recommended IDE [Visual Studio Code](https://code.visualstudio.com/download)
- Download [Node.js](https://nodejs.org/en/download).
- Check ```node``` and ```npm``` installed succesfylly using ```node -v``` and ```npm -v```.

## Setup
### Project
- Choose a desired directory
- ```git clone https://github.com/Adam-Hamara/SocketVerse.git```
### Backend
1. Open ```backend``` directory in IntelliJ IDEA
2. In project tree open ```/src/main/java/com/socketverse/backend```
3. Right click ```Application.java``` press green right-arrow ``` run 'Application.main()'``` to enable automatic configuration of the service
#### To deploy selected only packages run  
- ```./mvnw clean package -P games -D enabled.games=magic8ball,pioupiou```
- where ```enabled.games=game1,game2,game3``` names of packages are defined in file ```pom.xml``` profiles
### Frontend
1. Open command line or powershell
2. ```cd /frontend```
3. ```npm install```
4. ```npm start```