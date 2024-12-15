import { IconPencil, IconTrash } from '@tabler/icons-react';
import {ActionIcon, Group, Loader, Table, Text, Notification, Box} from '@mantine/core';
import {useQuery} from "@tanstack/react-query";
import {fetchTeachers} from "../api/service.ts";


export default function ListTeacherComponent() {
    const {status, data, error } = useQuery(["teachers"], fetchTeachers);
    if(status === "loading") {
        return (
        <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
            <Loader size="lg"/>
        </Box>
        )
    } else if (status === "error") {
        return (
        <Notification color="red" title="Error loading">
            {error.message || 'An unknown error occurred.'}
        </Notification>
        )
    }
    const teachers = data.data
    const rows = teachers.map((item) => (
        <Table.Tr key={item.firstName + " " + item.lastName}>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.firstName + " " + item.lastName}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap={0} justify="flex-end">
                    <ActionIcon variant="subtle" color="gray">
                        <IconPencil size={16} stroke={1.5} />
                    </ActionIcon>
                    <ActionIcon variant="subtle" color="red">
                        <IconTrash size={16} stroke={1.5} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}