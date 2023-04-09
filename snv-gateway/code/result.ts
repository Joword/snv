import {StatusCode} from "./status";
import {ApiProperty} from "@nestjs/swagger";

export class ResultData {
    @ApiProperty({type: 'number', default: 200})
    code: number;
    @ApiProperty({type: 'string', default: 'success'})
    msg: string;
    data: any;

    constructor(code = 200, msg?: string, data?: any) {
        this.code = code;
        this.msg = msg || 'success';
        this.data = data || null;
    }

    static success(msg?: string, data?: any): ResultData {
        return new ResultData(StatusCode.SERVICE_SUCCESS, msg, data);
    }

    static failed(code: number, msg?: string, data?: any): ResultData {
        return new ResultData(code || 500, msg || 'Service exception', data);
    }
}