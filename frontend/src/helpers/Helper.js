function generateUUID8() {
    return crypto.getRandomValues(new Uint32Array(1))[0].toString(16).padStart(8, "0");
}

export default generateUUID8;