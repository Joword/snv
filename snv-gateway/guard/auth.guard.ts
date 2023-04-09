import {CanActivate, Injectable, ExecutionContext} from "@nestjs/common";
import {Reflector} from "@nestjs/core";
import {ConfigService} from "@nestjs/config";

@Injectable()
export class RolesGuard implements CanActivate {
    private globalWhiteList = []

    constructor(
        private readonly reflector: Reflector,
        private readonly config: ConfigService,
    ) {
        this.globalWhiteList = [].concat();
    }

    async canActivate(context: ExecutionContext): Promise<boolean> {
        const request = context.switchToHttp().getRequest();


        return true;
    }
}