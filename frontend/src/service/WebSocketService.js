class WebSocketService {
    constructor() {
      if (!WebSocketService.instance) {
        this.socket = null;
        this.listeners = {};
        WebSocketService.instance = this;
      }
      return WebSocketService.instance;
    }
  
    connect(url) {
      if (!this.socket || this.socket.readyState === WebSocket.CLOSED) {
        this.socket = new WebSocket(url);
  
        this.socket.onopen = () => {
          console.log("Connected to WebSocket");
        };
  
        this.socket.onmessage = (event) => {
          console.log("Message received:", event.data);
          Object.values(this.listeners).forEach((callback) => callback(event.data));
        };
  
        this.socket.onclose = () => {
          console.log("Disconnected from WebSocket");
        };
      }
    }
  
    send(message) {
      if (this.socket && this.socket.readyState === WebSocket.OPEN) {
        this.socket.send(JSON.stringify(message));
      } else {
        console.warn("WebSocket is not connected.");
      }
    }
  
    addListener(id, callback) {
      this.listeners[id] = callback;
    }

    containsListener(id){
      return this.listeners[id] !== undefined;
    }
  
    removeListener(id) {
      delete this.listeners[id];
    }
  
    disconnect() {
      if (this.socket) {
        this.socket.close();
        this.socket = null;
      }
    }
  }
  
  // Export a single instance
  const webSocketService = new WebSocketService();
  export default webSocketService;
  